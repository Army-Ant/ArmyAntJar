package ArmyAnt.NeuronNetwork;

import java.util.function.Function;

/**
 * Created by Jason-Zhao-Jie on 2016/5/21.
 */
public class Neuron {
    public static Function<Double, Double> GetLinerFunction(double slope, double bias) {
        return (Double input) -> input * slope + bias;
    }

    public static Function<Double, Double> GetLinerFunction(double slope) {
        return GetLinerFunction(slope, 0);
    }

    public static Function<Double, Double> GetRampFunc(double width, double height) {
        return (Double input) -> {
            if (input > width)
                return height;
            if (input < -width)
                return -height;
            return input * height / width;
        };
    }

    public static Function<Double, Double> GetThresholdFunc(double amplitude, double threshold) {
        return (Double input) -> input > threshold ? amplitude : 0;
    }

    public static Function<Double, Double> GetSigmoidFunc(double coef){
        return (Double input) -> 1 / (1 + java.lang.Math.pow(ArmyAnt.ArmyAnt.c_natrualBase, -coef*input));
    }

    public static Function<Double, Double> GetDoubleSigmoidFunc(double coef){
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

    public void SetThreshold(double threshold)
    {
        this.threshold = threshold;
    }
    public double GetThreshold()
    {
        return threshold;
    }
    public void SetActiveFunction(Function<Double, Double> activeFunc)
    {
        this.activeFunc = activeFunc;
    }

    public boolean JoinActive(double input, double weight)
    {
        inputTotal += input*weight;
        return true;
    }
    public boolean LeaveActive(double input, double weight)
    {
        inputTotal -= input*weight;
        return true;
    }

    public void ClearActive()
    {
        inputTotal = 0;
    }

    public double GetActiveTotal()
    {
        return inputTotal;
    }

    public double GetOutPut()
    {
        return activeFunc.apply(inputTotal);
    }
}
