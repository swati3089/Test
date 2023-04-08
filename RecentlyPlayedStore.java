import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentlyPlayedStore {
    private int capacity;
    private Map<String, List<String>> userSongsMap;
    private Map<String, Integer> songTimestampMap;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.userSongsMap = new HashMap<>();
        this.songTimestampMap = new HashMap<>();
    }

    public void addSong(String user, String song) {
        // Check if user exists in the map
        if (!userSongsMap.containsKey(user)) {
            // Create a new list for the user if they don't have one
            userSongsMap.put(user, new ArrayList<>());
        }
        List<String> songsList = userSongsMap.get(user);

        // Check if song is already in the list
        if (songsList.contains(song)) {
            // If it is, update the timestamp and return
            songTimestampMap.put(song, (int) System.currentTimeMillis());
            return;
        }

        // Add the song to the list and update the timestamp map
        songsList.add(song);
        songTimestampMap.put(song, (int) System.currentTimeMillis());

        // If the list size exceeds the capacity, remove the least recently played song
        if (songsList.size() > capacity) {
            String leastRecentlyPlayedSong = null;
            int leastRecentTimestamp = Integer.MAX_VALUE;
            for (String s : songsList) {
                int timestamp = songTimestampMap.get(s);
                if (timestamp < leastRecentTimestamp) {
                    leastRecentTimestamp = timestamp;
                    leastRecentlyPlayedSong = s;
                }
            }
            songsList.remove(leastRecentlyPlayedSong);
            songTimestampMap.remove(leastRecentlyPlayedSong);
        }
    }

    public List<String> getRecentSongs(String user) {
        // Check if user exists in the map
        if (!userSongsMap.containsKey(user)) {
            return new ArrayList<>();
        }
        List<String> songsList = userSongsMap.get(user);

        // Sort the songs list by timestamp in descending order
        songsList.sort((s1, s2) -> {
            int t1 = songTimestampMap.get(s1);
            int t2 = songTimestampMap.get(s2);
            return Integer.compare(t2, t1);
        });

        return songsList;
    }
}
