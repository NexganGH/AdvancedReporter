package net.Nexgan.AdvancedReporter.reports;

import net.Nexgan.AdvancedReporter.playerdata.PlayerData;
import net.Nexgan.AdvancedReporter.sections.Section;
import net.Nexgan.AdvancedReporter.sections.SubSection;

/**
 * Represents the report created by the players.
 */
public class ReportTicket {

	private int id;
	private PlayerData reporter;
	private PlayerData reported;
	private String serverName;
	private Section section;
	private SubSection subSection;
	private String reason;
	private PlayerData manager;
	private boolean pending;
	private boolean accepted;
	private String howResolved;

	public ReportTicket(int id, PlayerData reporter, PlayerData reported, String serverName, Section section, SubSection subSection, String reason, PlayerData manager, boolean pending, boolean accepted, String howResolved) {
		this.id = id;
		this.reporter = reporter;
		this.reported = reported;
		this.serverName = serverName;
		this.section = section;
		this.subSection = subSection;
		this.reason = reason;
		this.manager = manager;
		this.pending = pending;
		this.accepted = accepted;
		this.howResolved = howResolved;
	}

    public int getId() {
        return id;
    }

    public PlayerData getReporter() {
        return reporter;
    }

    public PlayerData getReported() {
        return reported;
    }

    public String getServerName() {
        return serverName;
    }

    public Section getSection() {
        return section;
    }

    public SubSection getSubSection() {
        return subSection;
    }

    public String getReason() {
        return reason;
    }

    public PlayerData getManager() {
        return manager;
    }

    public boolean isPending() {
        return pending;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getHowResolved() {
        return howResolved;
    }
}
