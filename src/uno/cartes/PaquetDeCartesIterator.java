package uno.cartes;
import java.util.Iterator;
import java.util.ArrayList;

public class PaquetDeCartesIterator implements Iterator<Carte> {
    private ArrayList<Carte> cartes;
    private int currentIndex = 0;

    public PaquetDeCartesIterator(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < cartes.size();
    }

    @Override
    public Carte next() {
        if (hasNext()) {
            return cartes.get(currentIndex++);
        } else {
            throw new UnsupportedOperationException("No more elements in the iterator.");
        }
    }
}
