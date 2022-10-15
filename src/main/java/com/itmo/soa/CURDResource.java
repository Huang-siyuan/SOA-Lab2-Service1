package com.itmo.soa;

import com.itmo.soa.exceptions.ValidationException;
import com.itmo.soa.models.Coordinates;
import com.itmo.soa.models.DAO.CoordinatesDAO;
import com.itmo.soa.models.DAO.LocationDAO;
import com.itmo.soa.models.DAO.RouteDAO;
import com.itmo.soa.models.Location;
import com.itmo.soa.models.Route;
import com.itmo.soa.models.mappers.CoordinatesMapper;
import com.itmo.soa.models.mappers.LocationMapper;
import com.itmo.soa.models.mappers.RouteMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;

@Path("/routes")
public class CURDResource {



//    @POST
//    @Produces({MediaType.APPLICATION_XML})
//    @Path("/")
//    public String createRoute(@FormParam("route") String route) throws IOException {
//        RouteMapper routeMapper = getRouteMapper();
//
//    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/")
    public String getRoute() {
        return "Hello, World!";
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Route getRouteById(@PathParam("id") long id) throws IOException {
        RouteMapper routeMapper = getRouteMapper();
        if(id <= 0) {
            throw new ValidationException("id must be positive");
        }
        RouteDAO route = routeMapper.findRouteById(id);
        if(route == null) {
            throw new ValidationException("route with id " + id + " not found");
        }
        return toRoute(route);
    }

    @PUT
    @Produces({MediaType.APPLICATION_XML})
    @Path("/{id}")
    public String updateRoute() {
        return "Hello, World!";
    }

    @DELETE
    @Produces({MediaType.APPLICATION_XML})
    @Path("/{id}")
    public String deleteRoute() {
        return "Hello, World!";
    }

    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Path("/distances/sum")
    public String getSumDistance() {
        return "Hello, World!";
    }

    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Path("/distances/less/{value}")
    public String getLessDistance() {
        return "Hello, World!";
    }

    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Path("/distances/unique")
    public String getUniqueDistance() {
        return "Hello, World!";
    }

    private RouteMapper getRouteMapper() throws IOException {
        return getSqlSession().getMapper(RouteMapper.class);
    }

    private LocationMapper getLocationMapper() throws IOException {
        return getSqlSession().getMapper(LocationMapper.class);
    }

    private CoordinatesMapper getCoordinatesMapper() throws IOException {
        return getSqlSession().getMapper(CoordinatesMapper.class);
    }

    private SqlSession getSqlSession() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(is);
        return sqlSessionFactory.openSession(true);
    }

    private Route toRoute(RouteDAO routeDAO) throws IOException {
        RouteMapper routeMapper = getRouteMapper();
        LocationMapper locationMapper = getLocationMapper();
        CoordinatesMapper coordinatesMapper = getCoordinatesMapper();

        Location locationFrom = toLocation(locationMapper.findLocationDAOById(routeDAO.getFrom_id()));
        Location locationTo = toLocation(locationMapper.findLocationDAOById(routeDAO.getTo_id()));
        Coordinates coordinates = toCoordinates(coordinatesMapper.findCoordinatesDAOById(routeDAO.getCoordinates_id()));

        return new Route(routeDAO.getId(), routeDAO.getName(), coordinates, routeDAO.getCreationDate(), locationFrom, locationTo, routeDAO.getDistance());
    }

    private Location toLocation(LocationDAO locationDAO) {
        return new Location(locationDAO.getX(), locationDAO.getY(), locationDAO.getZ(),locationDAO.getName());
    }

    private Coordinates toCoordinates(CoordinatesDAO coordinatesDAO) {
        return new Coordinates(coordinatesDAO.getX(), coordinatesDAO.getY());
    }
}