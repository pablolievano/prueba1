package co.edu.usbcali.banco.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.domain.TipoUsuario;
import co.edu.usbcali.banco.domain.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestUsuarioRepository {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	
	String usuUsuario = "usuario_prueba";
	BigDecimal identificacion = new BigDecimal("123456789");
	
	@DisplayName("CrearUsuario")
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void aTest() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);
		
		Usuario usuario = usuarioRepository.findById(usuUsuario);
		assertNull(usuario, "El usuario no es nulo");
		
		usuario = new Usuario();
		usuario.setActivo("N");
		usuario.setUsuUsuario(usuUsuario);
		usuario.setClave("Prueba2018");
		usuario.setIdentificacion(identificacion);
		usuario.setNombre("Usuario de prueba");
		
		TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(1L);
		assertNotNull(tipoUsuario, "El tipo de usuario es nulo");
		
		usuario.setTipoUsuario(tipoUsuario);
		
		usuarioRepository.save(usuario);
	}
	
	@DisplayName("ModificarUsuario")
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void bTest() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);
		
		Usuario usuario = usuarioRepository.findById(usuUsuario);
		assertNotNull(usuario, "El usuario no es nulo");
		
		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setUsuUsuario(usuUsuario);
		usuario.setClave("Prueba2019");
		usuario.setIdentificacion(identificacion);
		usuario.setNombre("Usuario de prueba tres");
		
		TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(1L);
		assertNotNull(tipoUsuario, "El tipo de usuario es nulo");
		
		usuario.setTipoUsuario(tipoUsuario);
		
		usuarioRepository.update(usuario);
	}
	
	@DisplayName("BorrarUsuario")
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void cTest() {
		assertNotNull(usuarioRepository);
		
		Usuario usuario = usuarioRepository.findById(usuUsuario);
		assertNotNull(usuario, "El usuario es nulo");
		
		usuarioRepository.delete(usuario);
	}
	
	/*private final static Logger log = LoggerFactory.getLogger(TestUsuarioRepository.class);
	@DisplayName("ConsultarTodos")
	@Test
	@Transactional(readOnly=true)
	void dTest() {
		assertNotNull(usuarioRepository);
		
		List<Usuario> losUsuarios = usuarioRepository.findAll();
		
		for (Usuario usuario : losUsuarios) {
			log.info("Usuario:"+usuario.getUsuUsuario());
			log.info("Nombre:"+usuario.getNombre());
		}
		
		losUsuarios.forEach(usuario->{
			log.info("Usuario:"+usuario.getUsuUsuario());
			log.info("Nombre:"+usuario.getNombre());
		});
	}*/

}
