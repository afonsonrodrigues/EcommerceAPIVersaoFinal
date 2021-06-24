package org.serratec.resources;

import org.serratec.dto.LoginDTO;
import org.serratec.dto.TokenDTO;
import org.serratec.models.Cliente;
import org.serratec.repository.ClienteRepository;
import org.serratec.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Optional;

@RestController
@Api(value = "API - Autentica��o")
public class AuthResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@ApiOperation(value = "Gera��o do token de autentica��o a partir de username e senha")
	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody @Validated LoginDTO loginDTO){
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUser(), loginDTO.getPass());
		
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		String token = tokenService.generateToken(authentication);		
		
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setToken(token);
		tokenDTO.setType("Bearer");
		Optional<Cliente> optional = clienteRepository.findByEmail(loginDTO.getUser());
		if (optional.isPresent()) {
			tokenDTO.setCliente(optional.get());
		}
		
		return new ResponseEntity<>(tokenDTO, HttpStatus.OK);		
	}

}
