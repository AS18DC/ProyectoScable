public class Partida {
        private String alias;
        private boolean gano;
        private int puntos;
        private long tiempoTotal;
        private int palabrasColocadas;

        public Partida(String alias, int puntos, boolean gano, long tiempoTotal, int palabrasColocadas) {
            this.alias = alias;
            this.puntos = puntos;
            this.gano = gano;
            this.tiempoTotal = tiempoTotal;
            this.palabrasColocadas = palabrasColocadas;
        }

        public String getAlias() {return this.alias;}

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public boolean isGano() {
            return this.gano;
        }

        public void setGano(boolean gano) {
            this.gano = gano;
        }

        public int getPuntos() {
            return this.puntos;
        }

        public void setPuntos(int puntos) {
            this.puntos = puntos;
        }

        public long getTiempoTotal() {
            return this.tiempoTotal;
        }

        public void setTiempoTotal(long tiempoTotal) {
            this.tiempoTotal = tiempoTotal;
        }

        public int getPalabrasColocadas() {
            return this.palabrasColocadas;
        }

        public void setPalabrasColocadas(int palabrasColocadas) {
            this.palabrasColocadas = palabrasColocadas;
        }
    }
