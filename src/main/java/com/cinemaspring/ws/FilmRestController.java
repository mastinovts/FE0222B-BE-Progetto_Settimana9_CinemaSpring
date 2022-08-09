package com.cinemaspring.ws;

import java.util.List;

import com.cinemaspring.dao.FilmDAO;
import com.cinemaspring.dao.impl.FilmDAOImpl;
import com.cinemaspring.model.Film;
import com.cinemaspring.util.FilmDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cinema")
@Api(value="FilmRest", tags="Servizio Rest Per i Film")
public class FilmRestController {
	
	@ApiOperation(value = "Registrazione Nuovo Film", notes = "Permette l' inserimento di un Nuovo Film", response = String.class, produces = "application/json")
    @ApiResponses(value ={ @ApiResponse(code = 200, message = "Inserimento Effettuato!") })
	@PostMapping("/insert")
	public ResponseEntity <String> save(@RequestBody Film f){
		FilmDAO filmDAO =new FilmDAOImpl();
		filmDAO.insert(f);
		
		return new ResponseEntity <String>("Inserimento effettuato", HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Eliminazione Film", notes = "Permette la Cancellazione Di Un Film", response = String.class, produces = "application/json")
    @ApiResponses(value ={ @ApiResponse(code = 200, message = "Eliminazione effettuata!") })
	@DeleteMapping("/delete")
	public ResponseEntity <String> delete(@RequestParam int id){
		FilmDAO filmDAO = new FilmDAOImpl();
		filmDAO.delete(id);
		
		return new ResponseEntity <String>("Eliminazione effettuata", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Ricerca Film Tramite Parametro Regista", notes = "Permette la Ricerca Di Un Film Tramite il suo Regista", response = String.class, produces = "application/json")
    @ApiResponses(value ={ @ApiResponse(code = 200, message = "Ricerca completata") })
	@GetMapping("/regista")
	public ResponseEntity <List <Film>> findByRegista(@RequestBody FilmDTO fDTO){
		FilmDAO fDAO = new FilmDAOImpl();
		
		return new ResponseEntity <List<Film>>(fDAO.findByRegista(fDTO.getRegista()), HttpStatus.OK);
				
	}

}
