package org.david.sakk;

public class SakkMain {
	static int URESMEZO = 0;
	static int FEHERGYALOG = 1;
	static int FEHERBASTYA = 2;
	static int FEHERLOVAG = 3;
	static int FEHERFUTO = 4;
	static int FEHERVEZER = 5;
	static int FEHERKIRALY = 6;

	static int FEKETEGYALOG = 7;
	static int FEKETEBASTYA = 8;
	static int FEKETELOVAG = 9;
	static int FEKETEFUTO = 10;
	static int FEKETEVEZER = 11;
	static int FEKETEKIRALY = 12;

	static int[] TABLA = new int[8 * 8];

	static int TABLASZELESSEG = 8;

	static void kezdoTablatFelrak(int[] TABLA) {
		TABLA[0] = FEKETEBASTYA;
		TABLA[1] = FEKETELOVAG;
		TABLA[2] = FEKETEFUTO;
		TABLA[3] = FEKETEVEZER;
		TABLA[4] = FEKETEKIRALY;
		TABLA[5] = FEKETEFUTO;
		TABLA[6] = FEKETELOVAG;
		TABLA[7] = FEKETEBASTYA;
		TABLA[8] = FEKETEGYALOG;
		TABLA[9] = FEKETEGYALOG;
		TABLA[10] = FEKETEGYALOG;
		TABLA[11] = FEKETEGYALOG;
		TABLA[12] = FEKETEGYALOG;
		TABLA[13] = FEKETEGYALOG;
		TABLA[14] = FEKETEGYALOG;
		TABLA[15] = FEKETEGYALOG;

		TABLA[48] = FEHERGYALOG;
		TABLA[49] = FEHERGYALOG;
		TABLA[50] = FEHERGYALOG;
		TABLA[51] = FEHERGYALOG;
		TABLA[52] = FEHERGYALOG;
		TABLA[53] = FEHERGYALOG;
		TABLA[54] = FEHERGYALOG;
		TABLA[55] = FEHERGYALOG;
		TABLA[56] = FEHERBASTYA;
		TABLA[57] = FEHERLOVAG;
		TABLA[58] = FEHERFUTO;
		TABLA[59] = FEHERVEZER;
		TABLA[60] = FEHERKIRALY;
		TABLA[61] = FEHERFUTO;
		TABLA[62] = FEHERLOVAG;
		TABLA[63] = FEHERBASTYA;
	}

	static int elemetVisszaado(int pozicio, int[] TABLA) {
		if (pozicio < 0 || pozicio >= TABLA.length) {
			return -1;
		}
		return TABLA[pozicio];
	}

	static int poziciotVisszaado(int x, int y) {
		int pozicio = y * TABLASZELESSEG + x;
		return pozicio;
	}

	static int elemetVisszaadoXY(int x, int y) {
		return elemetVisszaado(poziciotVisszaado(x, y), TABLA);
	}

	static boolean tablanBelulVanEALepes(int honnanX, int honnanY, int hovaX, int hovaY) {
		return (Math.min(hovaX, hovaY) >= 0) && (Math.max(hovaX, hovaY) < TABLASZELESSEG)
				&& (Math.min(honnanX, honnanY) >= 0) && (Math.max(honnanX, honnanY) < TABLASZELESSEG);
	}

	static boolean azonosSzinuBabuE(int honnanX, int honnanY, int hovaX, int hovaY) {
		int hovaBabuTipus = elemetVisszaadoXY(hovaX, hovaY);
		int honnanBabuTipus = elemetVisszaadoXY(honnanX, honnanY);

		if ((hovaBabuTipus >= FEHERGYALOG) && (hovaBabuTipus <= FEHERKIRALY) && (honnanBabuTipus >= FEHERGYALOG)
				&& (honnanBabuTipus <= FEHERKIRALY)) {
			System.out.println("azonos szinû bábu!");
			return true;
		}
		if ((hovaBabuTipus >= FEKETEGYALOG) && (hovaBabuTipus <= FEKETEKIRALY) && (honnanBabuTipus >= FEKETEGYALOG)
				&& (honnanBabuTipus <= FEKETEKIRALY)) {
			System.out.println("azonos szinû bábu!");
			return true;
		}
		return false;
	}

	static boolean ervenyesLepesE(int honnanX, int honnanY, int hovaX, int hovaY) {
		return tablanBelulVanEALepes(honnanX, honnanY, hovaX, hovaY)
				&& (!azonosSzinuBabuE(honnanX, honnanY, hovaX, hovaY) || (elemetVisszaadoXY(hovaX, hovaY) == URESMEZO));
	}

	static boolean sakkbanVanEAKiraly() { // 
		for (int kiralyY = 0; kiralyY < TABLASZELESSEG; ++kiralyY) {
			for (int kiralyX = 0; kiralyX < TABLASZELESSEG; ++kiralyX) {
				int aktualisBabu = elemetVisszaadoXY(kiralyX, kiralyY);
				if (aktualisBabu == FEHERKIRALY) {
					System.out.println("megvan a fehér király: ");
					letezikEFeketeSakkotAdoBabu(kiralyX, kiralyY);
				}
			}
		}
		for (int kiralyY = 0; kiralyY < TABLASZELESSEG; ++kiralyY) { //
			for (int kiralyX = 0; kiralyX < TABLASZELESSEG; ++kiralyX) {
				int aktualisBabu = elemetVisszaadoXY(kiralyX, kiralyY);
				if (aktualisBabu == FEKETEKIRALY) {
					System.out.println("megvan a fekete király: ");
					letezikEFeherSakkotAdoBabu(kiralyX, kiralyY);
				}
			}
		}
		return false;
	}

	static boolean letezikEFeketeSakkotAdoBabu(int kiralyX, int kiralyY) { //
		for (int sakkotAdoY = 0; sakkotAdoY < TABLASZELESSEG; ++sakkotAdoY) {
			for (int sakkotAdoX = 0; sakkotAdoX < TABLASZELESSEG; ++sakkotAdoX) {
				int aktualisBabu = elemetVisszaadoXY(sakkotAdoX, sakkotAdoY);
				if (aktualisBabu >= FEKETEGYALOG && aktualisBabu <= FEKETEKIRALY) {
					System.out.println(lepes(sakkotAdoX, sakkotAdoY, kiralyX, kiralyY));
					return lepes(sakkotAdoX, sakkotAdoY, kiralyX, kiralyY);
				}
			}
		}
		return false;
	}

	static boolean letezikEFeherSakkotAdoBabu(int kiralyX, int kiralyY) { //
		for (int sakkotAdoY = 0; sakkotAdoY < TABLASZELESSEG; ++sakkotAdoY) {
			for (int sakkotAdoX = 0; sakkotAdoX < TABLASZELESSEG; ++sakkotAdoX) {
				int aktualisBabu = elemetVisszaadoXY(sakkotAdoX, sakkotAdoY);
				if (aktualisBabu >= FEHERGYALOG && aktualisBabu <= FEHERKIRALY) {
					System.out.println(lepes(sakkotAdoX, sakkotAdoY, kiralyX, kiralyY));
					return lepes(sakkotAdoX, sakkotAdoY, kiralyX, kiralyY);
				}
			}
		}
		return false;
	}

	static boolean lepes(int honnanX, int honnanY, int hovaX, int hovaY) {
		int babuTipus = elemetVisszaadoXY(honnanX, honnanY);

		if (honnanX < 0 || honnanY < 0 || honnanX >= TABLASZELESSEG || honnanY >= TABLASZELESSEG) {
			System.out.println("Táblán kivül van a honnan pozicio");
			return false;
		}
		if (babuTipus == URESMEZO) {
			System.out.println("Nincs bábu a mezõn!");
			return false;
		}
		if (babuTipus == FEHERGYALOG) {
			System.out.println("Fehér gyalog lép");
			return feherGyalogLepese(honnanX, honnanY, hovaX, hovaY);
		} else if (babuTipus == FEHERBASTYA || babuTipus == FEKETEBASTYA) {
			System.out.println("Bástya lép");
			return bastyaLepese(honnanX, honnanY, hovaX, hovaY);
		} else if (babuTipus == FEHERLOVAG || babuTipus == FEKETELOVAG) {
			System.out.println("Lovag lép");
			return lovagLepese(honnanX, honnanY, hovaX, hovaY);
		} else if (babuTipus == FEHERFUTO || babuTipus == FEKETEFUTO) {
			System.out.println("Futó lép");
			return futoLepese(honnanX, honnanY, hovaX, hovaY);
		} else if (babuTipus == FEHERVEZER || babuTipus == FEKETEVEZER) {
			System.out.println("Vezér lép");
			return vezerLepese(honnanX, honnanY, hovaX, hovaY);
		} else if (babuTipus == FEHERKIRALY || babuTipus == FEKETEKIRALY) {
			System.out.println("Király lép");
			return kiralyLepese(honnanX, honnanY, hovaX, hovaY);
		} else if (babuTipus == FEKETEGYALOG) {
			System.out.println("Fekete gyalog lép");
			return feketeGyalogLepese(honnanX, honnanY, hovaX, hovaY); //
		}
		return false;
	}

	static boolean gyalogokErvenyesUtese(int honnanX, int honnanY, int hovaX, int hovaY) {
		return tablanBelulVanEALepes(honnanX, honnanY, hovaX, hovaY)
				&& !azonosSzinuBabuE(honnanX, honnanY, hovaX, hovaY) && elemetVisszaadoXY(hovaX, hovaY) != URESMEZO;
	}

	static boolean feherGyalogLepese(int honnanX, int honnanY, int hovaX, int hovaY) {
		boolean feherGyalogLepese = (tablanBelulVanEALepes(honnanX, honnanY, hovaX, hovaY))
				&& (elemetVisszaadoXY(hovaX, hovaY) == 0) && ((honnanX - hovaX == 0) && (hovaY - honnanY == -1));
		boolean feherGyalogUtese = gyalogokErvenyesUtese(honnanX, honnanY, hovaX, hovaY)
				&& (((hovaX - honnanX == -1) && (hovaY - honnanY == -1))
						|| ((hovaX - honnanX == 1) && (hovaY - honnanY == -1)));

		return feherGyalogLepese || feherGyalogUtese;
	}

	static boolean feketeGyalogLepese(int honnanX, int honnanY, int hovaX, int hovaY) {
		boolean feketeGyalogLepese = (tablanBelulVanEALepes(honnanX, honnanY, hovaX, hovaY))
				&& (elemetVisszaadoXY(hovaX, hovaY) == 0) && ((honnanX - hovaX == 0) && (hovaY - honnanY == 1));
		boolean feketeGyalogUtese = gyalogokErvenyesUtese(honnanX, honnanY, hovaX, hovaY)
				&& (((hovaX - honnanX == -1) || (hovaY - honnanY == 1))
						|| ((hovaX - honnanX == 1) && (hovaY - honnanY == 1)));
		return feketeGyalogLepese || feketeGyalogUtese;
	}

	static int irany(int a) {
		if (a > 0) {
			return 1;
		} else if (a < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	static boolean bastyaLepese(int honnanX, int honnanY, int hovaX, int hovaY) {
		int x = hovaX - honnanX;
		int y = hovaY - honnanY;
		boolean ervenyesLepesE = ervenyesLepesE(honnanX, honnanY, hovaX, hovaY);
		if (ervenyesLepesE && (x * y == 0 && x + y != 0)) {
			int deltaX = irany(x); // signum for direction
			int deltaY = irany(y);

			for (int kezdoX = honnanX + deltaX, kezdoY = honnanY + deltaY; kezdoX < hovaX
					|| kezdoY < hovaY; kezdoX += deltaX, kezdoY += deltaY) {
				if (elemetVisszaadoXY(kezdoX, kezdoY) != URESMEZO) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	static boolean lovagLepese(int honnanX, int honnanY, int hovaX, int hovaY) {
		return ervenyesLepesE(honnanX, honnanY, hovaX, hovaY)
				&& (Math.abs(hovaX - honnanX) == 2 && Math.abs(hovaY - honnanY) == 1)
				|| (Math.abs(hovaX - honnanX) == 1 && Math.abs(hovaY - honnanY) == 2);
	}

	static boolean futoLepese(int honnanX, int honnanY, int hovaX, int hovaY) {
		int x = hovaX - honnanX;
		int y = hovaY - honnanY;
		boolean ervenyesLepesE = ervenyesLepesE(honnanX, honnanY, hovaX, hovaY);

		if (ervenyesLepesE && (Math.abs(x) == Math.abs(y))) { 
			int deltaX = irany(x);
			int deltaY = irany(y);

			for (int kezdoX = honnanX + deltaX, kezdoY = honnanY + deltaY; kezdoX < hovaX
					|| kezdoY < hovaY; kezdoX += deltaX, kezdoY += deltaY) {
				if (elemetVisszaadoXY(kezdoX, kezdoY) != URESMEZO) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	static boolean vezerLepese(int honnanX, int honnanY, int hovaX, int hovaY) {
		return bastyaLepese(honnanX, honnanY, hovaX, hovaY) || futoLepese(honnanX, honnanY, hovaX, hovaY);
	}

	static boolean kiralyLepese(int honnanX, int honnanY, int hovaX, int hovaY) { 
		int x = Math.abs(hovaX - honnanX); 
		int y = Math.abs(hovaY - honnanY); 
		return ervenyesLepesE(honnanX, honnanY, hovaX, hovaY) && (x < 2 && y < 2 && x + y > 0);
	}

	static void tablaAllapota() {
		for (int i = 0; i < TABLA.length; ++i) {
			System.out.println(i + ": " + TABLA[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		kezdoTablatFelrak(TABLA);
		int honnanX = 2;
		int honnanY = 1;
		int hovaX = 2;
		int hovaY = 2;
		System.out.println(sakkbanVanEAKiraly());
	}
}