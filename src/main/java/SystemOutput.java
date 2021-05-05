import add_player.OutputBoundary;

class SystemOutput implements OutputBoundary {

    @Override
    public void writeOutputLine(String string) {
        System.out.println(string);
    }
}
