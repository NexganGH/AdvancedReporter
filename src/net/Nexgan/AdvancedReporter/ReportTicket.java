package net.Nexgan.AdvancedReporter;

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
}
