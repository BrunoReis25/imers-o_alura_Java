public enum Extratores {
      NASA(new ExtratorDeConteudoDaNasa(), "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14"), 
      IMDB(new ExtratorDeConteudoDoIMDB(), "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060");

      private final ExtratorDeConteudo extrator;
      private final String url;

      Extratores(ExtratorDeConteudo extrator, String url) {
            this.extrator = extrator;
            this.url = url;
      }

      public ExtratorDeConteudo getExtrator() {
            return extrator;
      }

      public String getUrl() {
            return url;
      }

}
