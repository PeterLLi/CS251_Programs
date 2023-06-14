package program07;

public class OrgDriver {
    public static void main(String[] args) {
        // Create the members
        Member member1 = new Member("Alice", 1);
        Member member2 = new Member("Bob", 2);
        Member member3 = new Member("Charlie", 3);

        // Create the leaders
        Leader leader1 = new Leader("Dave", 4, 1);
        Leader leader2 = new Leader("Eve", 5, 2);

        // Create the organizations
        Org organization1 = new Org("Organization 1", leader1);
        Org organization2 = new Org("Organization 2", leader2);

        // Create dummy organization - Organization 3
        Org organization3 = new Org();

        // Add members to tbe organization
        organization1.addMember(member1);
        organization1.addMember(member2);
        organization2.addMember(member2);
        organization2.addMember(member3);

        // Print out the organization 1 leaders
        System.out.println("Organization 1 leader: " );
        System.out.println("\tID: " + organization1.getLeader().getId() + ", Name: " + organization1.getLeader().getName()
                + ", Term: " + organization1.getLeader().getTerm());

        // Print out organization 1 members
        System.out.println(organization1.getOrgName() + " members:");
        for(Member member : organization1.getOrgMembers()) {
            System.out.println("\tID: " + member.getId() + ", Member name: " + member.getName());
        }

        // Console seperator
        System.out.println();

        // Print out organization 2 leaders
        System.out.println("Organization 2 leader: ");
        System.out.println("\tID: " + organization2.getLeader().getId() + ", Name: " + organization2.getLeader().getName()
                + ", Term: " + organization2.getLeader().getTerm());

        // Print out organization 2 members
        System.out.println(organization2.getOrgName() + " members:");
        for(Member member : organization2.getOrgMembers()) {
            System.out.println("\tID: " + member.getId() + ", Member name: " + member.getName());
        }

        // Console seperator
        System.out.println();

        // Change organization's leadership
        organization1.setLeader(leader2);
        organization2.setLeader(leader1);
        System.out.println("Organization 1 new leader: " + organization1.getLeader().getName());
        System.out.println("Organization 2 new leader: " + organization2.getLeader().getName());

        // Console seperator
        System.out.println();

        // Print out organization 3 leaders
        System.out.println("Organization 3 leader: ");
        System.out.println("\tID: " + organization3.getLeader().getId() + ", Name: " + organization3.getLeader().getName()
                + ", Term: " + organization3.getLeader().getTerm());

        // Print out organization 3 leaders
        // Expected output is 1 member - being the leader of organization 3. The name should match that of the previous line
        System.out.println(organization1.getOrgName() + " members:");
        for (Member member : organization3.getOrgMembers()) {
            System.out.println("\tID: " + member.getId() + ", Member name: " + member.getName());
        }
    }
}

