class NullOutputStream extends OutputStream {
	public void write(int b) {
		// Do nothing
	}
}

class NullPrintStream extends PrintStream {
	public NullPrintStream() {
		super(new NullOutputStream());
	}
}

class Application {
	private PrintStream debugout;
	public Application(PrintStream debugout) {
		this.debugout = debugout;
	}

	public void go() {
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += i;
			debugout.println("i = " + i);
		}
		System.out.println("sum = " + sum);
	}
}
