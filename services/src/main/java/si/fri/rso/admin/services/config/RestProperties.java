package si.fri.rso.admin.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;
import com.kumuluz.ee.configuration.cdi.producers.ConfigurationUtilProducer;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ConfigBundle("rest-properties")
@ApplicationScoped
public class RestProperties {

    @ConfigValue(watch = true)
    private Boolean maintenanceMode = false;


    private Boolean broken;

    public Boolean getMaintenanceMode() {
        return this.maintenanceMode;
    }

    public void setMaintenanceMode(final Boolean maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public Boolean getBroken() {
        return broken;
    }

    public void setBroken(final Boolean broken) {
        this.broken = broken;
    }
}