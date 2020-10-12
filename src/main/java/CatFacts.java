import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CatFacts {
    private List<CatFact> all;

    public CatFacts(@JsonProperty("all") List<CatFact> all) {
        this.all = all;
    }

    public List<CatFact> getAll() {
        return all;
    }

    public void setAll(List<CatFact> all) {
        this.all = all;
    }
}
