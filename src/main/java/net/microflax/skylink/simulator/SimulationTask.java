package net.microflax.skylink.simulator;

import net.microfalx.lang.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A task which invokes a simulator and persists the result.
 *
 * @param <T> the entity type
 */
class SimulationTask<T> implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationTask.class);

    private final AbstractSimulator<T> simulator;

    SimulationTask(AbstractSimulator<T> simulator) {
        this.simulator = simulator;
    }

    @Override
    public void run() {
        int range = Math.min(1, (int) (simulator.getSimulationCountPerIteration() * 0.3));
        int count = simulator.getSimulationCountPerIteration() - (range / 2) + ThreadLocalRandom.current().nextInt(range);
        int maxCount = count * 5;
        while (count > 0 || maxCount-- > 0) {
            try {
                simulator.next();
                count--;
            } catch (DataIntegrityViolationException e) {
                // ignore and retry, up to a limit
                LOGGER.info("Element already exists: {}", ExceptionUtils.getRootCauseMessage(e));
            }
        }
    }
}
