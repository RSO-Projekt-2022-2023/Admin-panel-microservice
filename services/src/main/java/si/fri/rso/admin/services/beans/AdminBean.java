package si.fri.rso.admin.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.admin.lib.Admin;
import si.fri.rso.admin.models.converters.AdminConverter;
import si.fri.rso.admin.models.entities.AdminEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RequestScoped
public class AdminBean {

    private Logger log = Logger.getLogger(AdminBean.class.getName());

    @Inject
    private EntityManager em;

    public List<Admin> getAdmin() {

        TypedQuery<AdminEntity> query = em.createNamedQuery(
                "AdminEntity.getAll", AdminEntity.class);

        List<AdminEntity> resultList = query.getResultList();

        return resultList.stream().map(AdminConverter::toDto).collect(Collectors.toList());

    }

    @Timed
    public List<Admin> getAdminFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, AdminEntity.class, queryParameters).stream()
                .map(AdminConverter::toDto).collect(Collectors.toList());
    }

    public Admin getAdmin(Integer id) {

        AdminEntity AdminEntity = em.find(AdminEntity.class, id);

        if (AdminEntity == null) {
            throw new NotFoundException();
        }

        Admin Admin = AdminConverter.toDto(AdminEntity);

        return Admin;
    }

    public Admin createAdmin(Admin Admin) {

        AdminEntity AdminEntity = AdminConverter.toEntity(Admin);

        try {
            beginTx();
            em.persist(AdminEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (AdminEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return AdminConverter.toDto(AdminEntity);
    }

    public Admin putAdmin(Integer id, Admin Admin) {

        AdminEntity c = em.find(AdminEntity.class, id);

        if (c == null) {
            return null;
        }

        AdminEntity updatedAdminEntity = AdminConverter.toEntity(Admin);

        try {
            beginTx();
            updatedAdminEntity.setId(c.getId());
            updatedAdminEntity = em.merge(updatedAdminEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return AdminConverter.toDto(updatedAdminEntity);
    }

    public boolean deleteAdmin(Integer id) {

        AdminEntity Admin = em.find(AdminEntity.class, id);

        if (Admin != null) {
            try {
                beginTx();
                em.remove(Admin);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
