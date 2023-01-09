package si.fri.rso.admin.api.v1.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("admin-config")
@ApplicationScoped
public class AdminProperties {

    @ConfigValue(watch = true)
    private String url;

    @ConfigValue(watch = true)
    private String apiKey;

    @ConfigValue(watch = true)
    private String senderMail;

    @ConfigValue(watch = true)
    private String receiverMail;

    @ConfigValue(watch = true)
    private String uporabnikiUrl;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return "20.105.42.67";
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setSenderMail(String senderMail) {
        this.senderMail = senderMail;
    }

    public String getSenderMail() {
        return senderMail;
    }

    public void setReceiverMail(String receiverMail) {
        this.receiverMail = receiverMail;
    }

    public String getReceiverMail() {
        return receiverMail;
    }

    public void setUporabnikiUrl(String uporabnikiUrl) {
        this.uporabnikiUrl = uporabnikiUrl;
    }

    public String getUporabnikiUrl() {
        return uporabnikiUrl;
    }
}