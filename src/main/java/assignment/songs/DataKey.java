package assignment.songs;

public class DataKey {
	private String songName;
	private int songSize;
	private int songGenre;

	// default constructor
	public DataKey() {
		this(null, 0, 0);
	}
        
	public DataKey(String name, int size, int genre) {
		songName = name;
		songSize = size;
		songGenre = genre;
	}

	public String getSongName() {
		return songName;
	}

	public int getSongSize() {
		return songSize;
	}

	public int getSongGenre() {
		return songGenre;
	}

	public String getSongGenreString(){
		switch (songGenre) {
			case 1:
				return "Soul";
			case 2:
				return "Hip-Hop";
			case 3:
				return "Classic Rock";
			case 4:
				return "Punk";
			case 5:
				return "Pop";
			case 6:
				return "Rock";
			default:
				return "Unknown";
		}
	}

	/**
	 * Returns 0 if this DataKey is equal to k, returns -1 if this DataKey is smaller
	 * than k, and it returns 1 otherwise. 
	 */
	public int compareTo(DataKey k) {
            if (this.getSongSize() == k.getSongSize()) {
                int compare = this.songName.toLowerCase().compareTo(k.songName.toLowerCase());
                if (compare == 0 || this.songName.toLowerCase().contains(k.songName.toLowerCase())) {
                     return 0;
                }
                else if (compare < 0) {
                    return -1;
                }
            }
            else if(this.getSongSize() < k.getSongSize()){
                    return -1;
            }
            return 1;
	}
	public int compareGenreTo(DataKey k) {
		if ((k.getSongGenre() == 0) | (this.getSongGenre() == k.getSongGenre())) {
			int compare = this.songName.toLowerCase().compareTo(k.songName.toLowerCase());
			if (compare == 0 || this.songName.toLowerCase().contains(k.songName.toLowerCase())) {
				return 0;
			}
			else if(this.getSongSize() < k.getSongSize()){
				return 1;
			}
		}
		else if(this.getSongSize() < k.getSongSize()){
			return 1;
		}
		return -1;
	}
}
