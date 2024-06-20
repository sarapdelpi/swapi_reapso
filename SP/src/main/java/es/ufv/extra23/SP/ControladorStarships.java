package es.ufv.extra23.SP;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class ControladorStarships {

    CrearJson crearJson = new CrearJson();
    public ArrayList<Starship> NewDatosSimple = new ArrayList<>();

    // POST de un dato nuevo del JSON Simple
    @PostMapping(path = "/starship",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Starship>> nuevoDato(@RequestBody ArrayList<Starship> nuevoDato) throws IOException {
        System.out.println("hola");
        System.out.println(nuevoDato);
        //this.NewDatosSimple.add(nuevoDato);
        NewDatosSimple = nuevoDato; //añado el nuevo dato a la lista
        CrearJson.GuardarDatos(NewDatosSimple); //el nuevo dato se guarda en el json
        return new ResponseEntity<ArrayList<Starship>>(this.NewDatosSimple, HttpStatus.CREATED);
    }

    // GET de los datos del JSON Simple
    @GetMapping("/starship")
    public ArrayList<Starship> GetDatos(){return this.NewDatosSimple;}



}
