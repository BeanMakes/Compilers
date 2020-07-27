public class Task1 {
    public static Language create(Matrix2D matrix2D) {

        Language result = new Language() {
            @Override
            public Boolean decide(int[] input) throws Task1Exception {
                int currState = 0;
                for (int i = 0; i < input.length; i++) {

                    if (i == 0) {
                        currState = matrix2D.nextState(matrix2D.initialState(), input[i]);
                    } else {
                        currState = matrix2D.nextState(currState, input[i]);
                    }
                }
                return currState == matrix2D.terminalState();
            }

        };
        return result;
    }
}