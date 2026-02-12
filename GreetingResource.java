package uce.edu.web.api.matricula;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }
}
//domain - Estudiante
/*package uce.edu.web.api.matricula.domain;

import java.time.LocalDateTime;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Estudiante")
@SequenceGenerator(name = "estudiante_seq", sequenceName = "estudiante_secuencia", allocationSize = 1)
public class Estudiante extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante_seq")
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDateTime fechaNacimiento;
    public String provincia;
    public String genero;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Hijo> hijos;
}
 */

// application - representation - EstudianteRepresentation
/*package uce.edu.web.api.matricula.application.representation;

import java.time.LocalDateTime;
import java.util.List;

public class EstudianteRepresentation {
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDateTime fechaNacimiento;
    public String provincia;
    public String genero;

    public List<LinkDto> links;
}
 */

// application - representation - LinkDto
/*package uce.edu.web.api.matricula.application.representation;

public class LinkDto {
    public String href;
    public String rel;

    public LinkDto() {
    }

    public LinkDto(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }
}
 */

// infraestruture - EstudianteRepository
/*package uce.edu.web.api.matricula.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Estudiante;

@ApplicationScoped
public class EstudianteRepository implements PanacheRepository<Estudiante> {

}
 */

// application - EstudianteService
/*package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante estu:this.estudianteRepository.listAll()){
            list.add(this.mapperToER(estu));
        }
        return list;
    }

    public EstudianteRepresentation consultarPorId(Integer id){
        return this.mapperToER(this.estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crear(EstudianteRepresentation estu){
        this.estudianteRepository.persist(this.mapperToEstudiante(estu));
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation est){
        Estudiante estu = this.estudianteRepository.findById(id.longValue());
        estu.apellido = est.apellido;
        estu.nombre = est.nombre;
        estu.fechaNacimiento = est.fechaNacimiento;
        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation est){
        Estudiante estu = this.estudianteRepository.findById(id.longValue());
        if(est.apellido != null){
            estu.apellido = est.apellido;
        }
        if(est.nombre != null){
            estu.nombre = est.nombre;
        }
        if(est.fechaNacimiento != null){
            estu.fechaNacimiento = est.fechaNacimiento;
        }
        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void eliminar(Integer id){
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvinciaGenero(String provincia, String genero) {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante estu:this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list()){
            list.add(this.mapperToER(estu));
        }
        return list;
    }

    private EstudianteRepresentation mapperToER(Estudiante est){
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.provincia = est.provincia;
        estuR.genero = est.genero;
        return estuR;
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation est){
        Estudiante estuR = new Estudiante();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.provincia = est.provincia;
        estuR.genero = est.genero;
        return estuR;
    }
}
 */

// interfaces - EstudianteResource
/*package uce.edu.web.api.matricula.interfaces;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;
    
    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "user", "docente"})
    public List<EstudianteRepresentation> listarTodos() {
        List <EstudianteRepresentation> lista = new ArrayList<>();
        for (EstudianteRepresentation er : this.estudianteService.listarTodos()){
            lista.add(this.construirLinks(er));
        }
        return lista;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    //@PermitAll
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer iden){
        return this.construirLinks(this.estudianteService.consultarPorId(iden));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response guardar(EstudianteRepresentation estu){
        this.estudianteService.crear(estu);
        return Response.status(Response.Status.CREATED).entity(estu).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Integer id, EstudianteRepresentation estu){
        this.estudianteService.actualizar(id, estu);
        return Response.status(Response.Status.OK).entity(estu).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizarParcial(@PathParam("id") Integer id, EstudianteRepresentation estu){
        this.estudianteService.actualizarParcial(id, estu);
        return Response.status(Response.Status.OK).entity(estu).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response borrar(@PathParam("id") Integer id){
        this.estudianteService.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<EstudianteRepresentation> buscarPorProvinciaGenero(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero) {
        return this.estudianteService.buscarPorProvinciaGenero(provincia, genero);
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HijoRepresentation> buscarPorIdEstudiante(@PathParam("id") Integer id){
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation er){
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id))
            .build().toString();
        
        String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id)).path("hijos")
            .build().toString();
            
        er.links = List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos"));
        return er;
    }
}
 */