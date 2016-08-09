package ArmyAnt.NeuronNetwork;

import java.util.function.Function;

/**
 * Created by Jason-Zhao-Jie on 2016/5/21.
 */
public class Neuron {
    public static Function<Double, Double> getLinerFunction(double slope, double bias) {
        return (Double input) -> input * slope + bias;
    }

    public static Function<Double, Double> getLinerFunction(double slope) {
        return getLinerFunction(slope, 0);
    }

    public static Function<Double, Double> getRampFunc(double width, double height) {
        return (Double input) -> {
            if (input > width)
                return height;
            if (input < -width)
                return -height;
            return input * height / width;
        };
    }

    public static Function<Double, Double> getThresholdFunc(double amplitude, double threshold) {
        return (Double input) -> input > threshold ? amplitude : 0;
    }

    public static Function<Double, Double> getSigmoidFunc(double coef){
        return (Double input) -> 1 / (1 + java.lang.Math.pow(ArmyAnt.ArmyAnt.c_natrualBase, -coef*input));
    }

    public static Function<Double, Double> getDoubleSigmoidFunc(double coef){
        return (Double input) -> 2 / (1 + java.lang.Math.pow(ArmyAnt.ArmyAnt.c_natrualBase, -coef*input)) - 1;
    }

    private double inputTotal = 0;
    protected double threshold = 0;
    protected Function<Double, Double> activeFunc = null;
    public Neuron(Function<Double, Double> activeFunc)
    {
        this.activeFunc = activeFunc;
    }
    public Neuron(Function<Double, Double> activeFunc, double threshold)
    {
        this.activeFunc = activeFunc;
        this.threshold = threshold;
    }

    public void setThreshold(double threshold)
    {
        this.threshold = threshold;
    }
    public double getThreshold()
    {
        return threshold;
    }
    public void setActiveFunction(Function<Double, Double> activeFunc)
    {
        this.activeFunc = activeFunc;
    }

    public boolean joinActive(double input, double weight)
    {
        inputTotal += input*weight;
        return true;
    }
    public boolean leaveActive(double input, double weight)
    {
        inputTotal -= input*weight;
        return true;
    }

    public void clearActive()
    {
        inputTotal = 0;
    }

    public double getActiveTotal()
    {
        return inputTotal;
    }

    public double getOutPut()
    {
        return activeFunc.apply(inputTotal);
    }
}
