package dev.davivieira;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/account")
public class AccountEndpoint {

    @Inject
    AccountRepository accountRepository;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(Account account) {
        accountRepository.persist(account);
    }

    @Path("/{email}")
    @GET
    public Account get(@PathParam("email") String email) {
        return accountRepository.findByEmail(email);
    }

    @Path("/all")
    @GET
    public List<Account> getAll() {
        return accountRepository.listAll();
    }

    @Path("/{email}")
    @Transactional
    @DELETE
    public void delete(@PathParam("email") String email) {
        accountRepository.deleteByEmail(email);
    }
}
