package br.senac.tads.dsw.usuariocrud.routes;

import br.senac.tads.dsw.usuariocrud.Usuario;
import br.senac.tads.dsw.usuariocrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserRoutes {

    @Autowired
    private UserRepository repositorio;

    @GetMapping("/usuarios")
    public ModelAndView buscarUsuarios() {
        List<Usuario> usuarios = repositorio.findAll();
        ModelAndView mv = new ModelAndView("gerenciamentoUsuario.html");
        mv.addObject("usuarios", usuarios);
        return mv;
    }

}
