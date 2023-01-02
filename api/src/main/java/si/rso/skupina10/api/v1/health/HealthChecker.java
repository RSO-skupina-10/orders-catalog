package si.rso.skupina10.api.v1.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import si.rso.skupina10.services.config.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HealthChecker implements HealthCheck {
    @Inject
    private RestProperties restProperties;

    @Override
    public HealthCheckResponse call() {
        if (restProperties.getBroken()) {
            return HealthCheckResponse.down(HealthChecker.class.getSimpleName());
        }
        else {
            return HealthCheckResponse.up(HealthChecker.class.getSimpleName());
        }
    }
}
