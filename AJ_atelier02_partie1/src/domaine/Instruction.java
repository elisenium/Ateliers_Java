package domaine;

import java.time.Duration;

public class Instruction {
    private String description;
    private Duration dureeEnMinute;

    public Instruction(String description, int dureeEnMinute) {
        this.description = description;
        this.dureeEnMinute = Duration.ofMinutes(dureeEnMinute);
    }

    public String getDescription() {
        return description;
    }

    public Duration getDureeEnMinute() {
        return dureeEnMinute;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDureeEnMinute(Duration dureeEnMinute) {
        this.dureeEnMinute = dureeEnMinute;
    }

    @Override
    public String toString() {
        return "(" + String.format("%02d:%02d",dureeEnMinute.toHours(),dureeEnMinute.toMinutesPart()) + ") " + description;
    }
}
