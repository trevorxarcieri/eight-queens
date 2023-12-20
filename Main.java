class Main {
  public static void main(String[] args) {
    SolutionFinder.hillClimb();
    SolutionFinder.exhaustiveSearch();
    // long avgNano = 0;
    // for (int i = 0; i < 10000; i++) {
    //   long start = System.nanoTime();
    //   SolutionFinder.hillClimb();
    //   avgNano = (avgNano * (i) + System.nanoTime() - start) / (i + 1);
    // }
    // System.out.println(avgNano);
  }
}