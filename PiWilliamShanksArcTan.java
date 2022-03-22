/*

Pi = 16 arctan (1/5) - 4 arctan (1/239)

arctan (1/x) = 1/x - 1/3x^3 + 1/5x^5 - 1/7x^7 + 1/9x^9 - .....


*/
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.MathContext;



public class PiWilliamShanksArcTan
{

   public static final int         NUM_ARCTANS_TERMS = 10;
   public static final BigDecimal  MINUS_ONE         = new BigDecimal ("-1");
   public static final MathContext PRECISION         = new MathContext(1000); // Decimal Places
   public static final BigDecimal  TWO               = new BigDecimal ("2");


   public static BigDecimal arcTan (int inDenominator) // Assume numerator = 1
   {
      //BigDecimal count  = BigDecimal.ZERO;
      BigDecimal result = BigDecimal.ZERO;

      int coefficient = 1;
      int sign        = 1;

      // arctan (1/x) = 1/x - 1/3x^3 + 1/5x^5 - 1/7x^7 + 1/9x^9 - .....
      for (int k = 0; k < NUM_ARCTANS_TERMS; k++)
      {
         BigDecimal  currDenominator = new BigDecimal ("" + inDenominator).pow        (coefficient);
         currDenominator             = new BigDecimal ("" + currDenominator).multiply (new BigDecimal ("" + coefficient) );

         BigDecimal  currTerm = BigDecimal.ONE.divide (currDenominator, PRECISION);
         currTerm    = new BigDecimal ("" + sign).multiply (currTerm);

         result      = result.add (currTerm);

         coefficient = coefficient + 2;
         sign        = sign * -1;

      }

      return result;
   }

   public static BigDecimal calculatePi ()
   {
      BigDecimal sixteen        = new BigDecimal ("16");
      BigDecimal four           = new BigDecimal ("4");
      BigDecimal arcTanOne5h    = arcTan (5);
      BigDecimal arcTanOne239th = arcTan (239);


      BigDecimal piPart1 = sixteen.multiply (arcTanOne5h);
      BigDecimal piPart2 = four.multiply    (arcTanOne239th);

      BigDecimal pi = piPart1.subtract (piPart2);

      return pi;
   }


   public static void main (String[] args)
   {
      System.out.println (calculatePi () );
   }
}