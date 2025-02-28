package Group13_cs4050_assignment2.songs;

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

	/** Compare specifically for genre searching, if the genre is either matching or 0, then string match the current and passed values,
	 * if the strings do not match, then as long as the current song size is less than the passed song size, return 1,
	 * if the strings do match, return 0. If the genres do not match, compare size, if the size of current is less, return 1, otherwise return -1 */
	 */
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
