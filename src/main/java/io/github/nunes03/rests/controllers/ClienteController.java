package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    @RequestMapping(
        value = "/hello",
        method = RequestMethod.POST,
        consumes = {"application/xml"},//Como vou receber
        produces = {"application/xml"}//Como vou retornar
    )
    @ResponseBody
    public Cliente helloCliente(@RequestBody Cliente cliente) {
        return cliente;
    }
}
