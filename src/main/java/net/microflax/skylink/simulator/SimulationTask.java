package net.microflax.skylink.simulator;

/**
 * A task which invokes a simulator and persists the result.
 *
 * @param <T> the entity type
 */
public class SimulationTask<T> implements Runnable {

    private final AbstractSimulator<T> simulator;

    public SimulationTask(AbstractSimulator<T> simulator) {
        this.simulator = simulator;
    }

    @Override
    public void run() {
        T result = simulator.simulate();
        simulator.save(result);
    }
}
