import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    String [] list;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            for (int i = 0; i <= list.length; i++){
            System.out.print(list[i]);
            }
        } else if (url.getPath().equals("/search")) {
            System.out.println("Not implemented yet!");
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=",2);
                if (parameters[0].equals("s")) {
                    num++;
                    list[num] = parameters[1];
                    return ("Added the String: "+parameters[1] +" and the total number of strings is now: " +num);
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
