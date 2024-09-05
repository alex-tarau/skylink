package net.microflax.skylink.passenger;

import net.microfalx.bootstrap.web.util.ExtendedUserDetails;
import net.microfalx.lang.StringUtils;
import net.microflax.skylink.AbstractService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static net.microfalx.lang.StringUtils.NA_STRING;
import static net.microfalx.lang.StringUtils.defaultIfEmpty;

@Service
public class PassengerService extends AbstractService<Passenger, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassengerService.class);

    @Autowired
    private PassengerRepository passengerRepository;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        Authentication authentication = success.getAuthentication();
        Object principal = authentication.getPrincipal();
        String username = null;
        String name = defaultIfEmpty(authentication.getName(), "Smith");
        String email = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        if (principal instanceof ExtendedUserDetails) {
            email = ((ExtendedUserDetails) principal).getEmail();
            name = ((ExtendedUserDetails) principal).getName();
        }
        if (StringUtils.isEmpty(username)) {
            LOGGER.warn("No security user could be extracted from principal " + authentication.getPrincipal());
            return;
        }
        int lastNamePosition = name.indexOf(' ');
        String firstName = name;
        String lastName = NA_STRING;
        if (lastNamePosition > 0) {
            firstName = name.substring(0, lastNamePosition);
            lastName = name.substring(lastNamePosition + 1);
        }
        Optional<Passenger> passengerResult = passengerRepository.findByUserName(username);
        if (passengerResult.isPresent()) return;
        Passenger passenger = new Passenger();
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setUserName(username);
        passenger.setEmail(defaultIfEmpty(email, firstName + "." + lastName + "@change.me"));
        passenger.setBirthDate(LocalDate.now().minusYears(18));
        passenger.setPassportNumber("000000000");
        try {
            passengerRepository.save(passenger);
        } catch (Exception e) {
            LOGGER.error("Failed to register passenger for user '" + username + "' (" + name + ")", e);
        }
    }

    public String generatePassportNumber() {
        return RandomStringUtils.random(1, true, false).toUpperCase() +
                RandomStringUtils.random(8, false, true).toUpperCase();
    }

}
