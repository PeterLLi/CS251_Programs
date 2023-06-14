package program07;

import java.util.*;
public class Org {
    private String orgName;
    private Leader leader;
    private List<Member> members;

    // Default leader variable
    private static Leader DEFAULT_LEADER = new Leader();
    private static String DEFAULT_ORG = "University of Wisconsin-Milwaukee";

    // Default constructor.
    public Org() {
        this(DEFAULT_ORG, DEFAULT_LEADER);
    }

    // Specifying constructor.
    public Org(String orgName, Leader leader) {
        this.setOrgName(orgName);
        this.setLeader(leader);
        this.members = new LinkedList<>();
    }

    // Accessors
    public String getOrgName() {
        return this.orgName;
    }

    public Leader getLeader() {
        return this.leader;
    }

    public List<Member> getMembers() {
        return this.members;
    }

    // Mutators
    protected void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    protected void setLeader(Leader leader) {
        this.leader = leader;
    }

    protected void addMember(Member member) {
        members.add(member);
    }

    protected void removeMember(Member member) {
        members.remove(member);
    }

    public List<Member> getOrgMembers(){
        List<Member> roster = new LinkedList<>();
        roster.add(this.leader);
        roster.addAll(members);

        return roster;
    }
}

