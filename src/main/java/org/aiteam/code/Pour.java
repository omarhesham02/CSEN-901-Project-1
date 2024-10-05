package org.aiteam.code;

public class Pour implements Operator<Integer> {

    private final Bottle source;
    private final Bottle destination;

    public Pour(Bottle source, Bottle destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public Integer apply(Object... args) {
        int pouredAmount = 0;

        // Check if the pour operation is valid
        if (source.getCurrentCapacity() == 0 || destination.getCurrentCapacity() == destination.getMaximumCapacity()) {
            return pouredAmount;
        }

        Color sourceTopLayer = source.getTopLayer();
        Color destinationTopLayer = destination.getCurrentCapacity() > 0 ? destination.getTopLayer() : null;

        if (destinationTopLayer != null && !sourceTopLayer.equals(destinationTopLayer)) {
            return pouredAmount;
        }

        // Perform the pour operation
        while (source.getCurrentCapacity() > 0 &&
               destination.getCurrentCapacity() < destination.getMaximumCapacity() &&
               (destinationTopLayer == null || sourceTopLayer.equals(destinationTopLayer))) {

            // Remove the top layer from the source bottle
            source.removeTopLayer();
            // Add the top layer to the destination bottle
            destination.addTopLayer(sourceTopLayer);
            pouredAmount++;

            // Update the top layers
            sourceTopLayer = source.getCurrentCapacity() > 0 ? source.getTopLayer() : null;
            destinationTopLayer = destination.getTopLayer();
        }

        return pouredAmount;
    }
}
