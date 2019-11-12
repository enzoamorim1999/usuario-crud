package br.senac.tads.dsw.usuariocrud.routes;

import br.senac.tads.dsw.usuariocrud.Papel;
import br.senac.tads.dsw.usuariocrud.Usuario;
import br.senac.tads.dsw.usuariocrud.repositories.PapelRepository;
import br.senac.tads.dsw.usuariocrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserRoutes {

    @Autowired
    private UserRepository repositorio;

    @Autowired
    private PapelRepository repositorioPapel;

    @GetMapping("/usuarios")
    public ModelAndView buscarUsuarios() {
        List<Usuario> usuarios = repositorio.findAll();
        ModelAndView mv = new ModelAndView("gerenciamentoUsuario.html");
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/criar")
    public ModelAndView paginaCriar() {
        List<Papel> papeis = repositorioPapel.findAll();
        ModelAndView mv = new ModelAndView("usuarioInclusao.html");
        mv.addObject("papeis", papeis);
        return mv;
    }

    @GetMapping("/editar/{idUsuario}")
    public ModelAndView paginaEDitar(@PathVariable("idUsuario") Long idUsuario) {
        Usuario usuario = repositorio.findById(idUsuario).orElse(null);
        ModelAndView mv = new ModelAndView("usuarioAlteracao.html");
        mv.addObject("Usuario", usuario);
        return mv;
    }

    @PostMapping("/usuario")
    public ModelAndView cadastrarUsuario(@ModelAttribute("usuario") Usuario request, @RequestParam("idsPapeis") final List<String> papeis) {
        Usuario usuario = repositorio.save(request);
        for (String papel: papeis) {
            Papel resultado = repositorioPapel.findById(Long.parseLong(papel)).orElse(null);
          //  usuario.getPapeis().add(resultado);
        }
        usuario = repositorio.save(usuario);
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        mv.addObject("Criado", "Sim");
        return mv;
    }

    @PutMapping("/editar/{idUsuario}")
    public ModelAndView editarUsuario(@PathVariable("idUsuario") Long idUsuario, @ModelAttribute("usuario") Usuario request) {
        request.setId(idUsuario);
        Usuario usuario = repositorio.save(request);
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        mv.addObject("Atualizado", "Sim");
        mv.addObject("Usuario", usuario);
        return mv;
    }



}
