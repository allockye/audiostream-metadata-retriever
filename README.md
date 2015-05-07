# audiostream-metadata-retriever

This is an android-library which allow you to retrieve metadata such as title and headers of audiostream as soon as possible.

### Support protocols
 - Icecast
 - Shoutcast wil be added later

## How to use
```
//set uri of your favourite audiostream
Uri uri = Uri.parse("http://...");

//implement callbacks
OnNewMetadataListener listener = new OnNewMetadataListener()
{
    @Override
    public void onNewHeaders(String stringUri, List<String> name, List<String> desc,
      List<String> br, List<String> genre, List<String> info) {}
      
    @Override
    public void onNewStreamTitle(String stringUri, String streamTitle) {}
}

//Start parsing
AudiostreamMetadataManager.getInstance()
    .setUri(uri)
    .setOnNewMetadataListener(listener)
    .setUserAgent(UserAgent.WINDOWS_MEDIA_PLAYER)
    .start();
    
//Stop parsing
AudiostreamMetadataManager.getInstance().stop();
```
*See [screenshots](/screenshots) and [sample](/app).*

### Support android APIs
- library: API 8+
- sample:  API 14+

---
## Gradle
Coming soon
