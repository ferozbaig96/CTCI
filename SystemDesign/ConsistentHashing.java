package SystemDesign;

/**
 * Consistent Hashing
 */

import java.util.*;

class ConsistentHashing
{
    static class BackendServer
    {
        String id;
        
        public BackendServer(String id)
        {
            this.id=id;
        }
    }
  
    int RING_SIZE;
    BackendServer ring[];
    int replicaCount;    // virtual servers

    private ConsistentHashing(int replicaCount, int ringSize, List<BackendServer> rackServers)
    {
        this.replicaCount = replicaCount;
        RING_SIZE = ringSize;
        ring = new BackendServer[RING_SIZE];

        for (BackendServer s : rackServers) {
            this.addServerToRing(s);
        }
    }
    
    // Hash Function
    // returns: hash(input) mod RING_SIZE
    public int getHash(String input)
    {
        // Get only positive hashes
        return (input.hashCode() & Integer.MAX_VALUE) % RING_SIZE;
    }
    
    void addServerToRing(BackendServer server)
    {
        for (int i=0; i<replicaCount; i++)
        {
            String stringToHash = i*i*i + "" + server.id + "" + i*i*i;
            int hashIndex = this.getHash(stringToHash);
            ring[hashIndex] = server;

            System.out.println("BackendServer "+server.id+" added to ring at index "+hashIndex);
        }
    }

    void removeServerFromRing(BackendServer server)
    {
        for (int i=0; i<replicaCount; i++)
        {
            String stringToHash = i*i*i + "" + server.id + "" + i*i*i;
            int hashIndex = this.getHash(stringToHash);
            ring[hashIndex] = null;
        }
    }
    
    BackendServer getServerForKey(String key)
    {
        BackendServer serverToRouteTo = null;

        if (ring.length == 0)
        {
            return null;
        }

        // get hash of key
        int hashKey = this.getHash(key);
        System.out.println("Hash of key "+key+" : "+hashKey);

        if(null != ring[hashKey])
        {
            serverToRouteTo = ring[hashKey];
        }
        else
        {
            // Find the first BackendServer in clockwise direction starting from hashKey

            boolean foundInFirstPartition = false;

            // finding in 1st partition of the ring (hashKey to last)
            for (int i=hashKey+1; i<RING_SIZE; i++)
            {
                if (null == ring[i])
                {
                    continue;
                }
                else
                {
                    foundInFirstPartition = true;
                    serverToRouteTo = ring[i];
                    break;
                }
            }

            // find in the remaining partition (start to hashKey)
            if (!foundInFirstPartition)
            {
                for (int i=0; i<hashKey; i++)
                {
                    if (null == ring[i])
                    {
                        continue;
                    }
                    else
                    {
                        serverToRouteTo = ring[i];
                        break;
                    }
                }
            }
        }

        return serverToRouteTo;
    }

    public static void main(String[] args)
    {
        List<BackendServer> rackServers = new ArrayList<>();
        BackendServer bs1 = new BackendServer("192.168.0.3");
        BackendServer bs2 = new BackendServer("192.168.0.4");
        BackendServer bs3 = new BackendServer("192.168.0.5");
        BackendServer bs4 = new BackendServer("192.168.0.6");
        rackServers.add(bs1);
        rackServers.add(bs2);
        rackServers.add(bs3);
        rackServers.add(bs4);

        int replicaCount = 50;
        // assuming collision will not happen
        int ringSize = 1000;

        ConsistentHashing ch = new ConsistentHashing(replicaCount, ringSize, rackServers);

        // add a new server
        BackendServer newServer = new BackendServer("192.168.0.8");
        ch.addServerToRing(newServer);

        // assume you have request with key key0
        BackendServer serverForKey = ch.getServerForKey("key0");
        System.out.println("BackendServer "+serverForKey.id+" is processing request: key0");

        // remove a server
        ch.removeServerFromRing(newServer);

        // Now check on which server "key0" landed up
        serverForKey = ch.getServerForKey("key0");
        System.out.println("BackendServer "+serverForKey.id+" is processing request: key0");
        
        // again removing servers and checking
        ch.removeServerFromRing(bs4);
        serverForKey = ch.getServerForKey("key0");
        System.out.println("BackendServer "+serverForKey.id+" is processing request: key0");
        
        ch.removeServerFromRing(bs3);
        serverForKey = ch.getServerForKey("key0");
        System.out.println("BackendServer "+serverForKey.id+" is processing request: key0");
        
        ch.removeServerFromRing(bs2);
        serverForKey = ch.getServerForKey("key0");
        System.out.println("BackendServer "+serverForKey.id+" is processing request: key0");

    }
}