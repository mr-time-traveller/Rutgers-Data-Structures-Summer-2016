package friends;

public class Person {
	String name;		// the person's name
	Friend firstFriend; // the first friend in the list of this
					    // person's friends
	Person nextPerson;  // the next person in the list of people
	
	public Person(String name, Person nextPerson) {
		this.name = name;
		this.nextPerson = nextPerson;
		}

	// A string representing the list of friends of this person.  
	// O(number of friends in the list)
	public String friendString(){
		String allFriends = "";
		for (Friend fr = firstFriend; fr!= null; fr = fr.nextFriend){
			allFriends = allFriends + fr.who.name+" ";
		}
		allFriends = allFriends + "";
		return allFriends;
	}
	
	// add friend as a friend of this person
	// O(1)
	public void addFriend(Person friend){
		
		Friend nFriend = new Friend(friend,firstFriend);
			firstFriend = nFriend;
				nFriend = new Friend(friend,firstFriend.nextFriend);
	}
	
	// remove Person friend as a friend of this person
	// if friend is not a friend of this person, does nothing
	// O(number of friends of this person)
	public void removeFriend(Person friend){
		Friend fr = firstFriend;
		if(firstFriend == null){
			return;
		}
		else if(fr.who.name.equals(friend.name) )
		 {
		      firstFriend = firstFriend.nextFriend;
		   }
		else{
		   Friend cur  = firstFriend;
		   Friend prev = null;

		   while(cur != null && !cur.who.name.equals(friend.name) )
		   {
		      prev = cur;
		      cur = cur.nextFriend;
		   }

		   //delete cur node
		   prev.nextFriend = cur.nextFriend;
		//firstFriend.nextFriend=firstFriend.nextFriend.nextFriend;
		}
	}
}
