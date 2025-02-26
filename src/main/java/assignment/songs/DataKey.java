package assignment.songs;

public class DataKey {
	private String songName;
	private int songGenre;

	// default constructor
	public DataKey() {
		this(null, 0);
	}
        
	public DataKey(String name, int size) {
		songName = name;
		songGenre = size;
	}

	public String getSongName() {
		return songName;
	}

	public int getSongGenre() {
		return songGenre;
	}

	/**
	 * Returns 0 if this DataKey is equal to k, returns -1 if this DataKey is smaller
	 * than k, and it returns 1 otherwise. 
	 */
	public int compareTo(DataKey k) {
            if (this.getSongGenre() == k.getSongGenre()) {
                int compare = this.songName.compareTo(k.getSongName());
                if (compare == 0){
                     return 0;
                } 
                else if (compare < 0) {
                    return -1;
                }
            }
            else if(this.getSongGenre() < k.getSongGenre()){
                    return -1;
            }
            return 1;
            
	}
}
