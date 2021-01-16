public class DBContainer extends PostgreSQLContainer<DBContainer> {
  private static final String IMAGE_VERSION = "postgres:11.1";
  private static BaeldungPostgresqlContainer container;

  private DBContainer() {
    super(IMAGE_VERSION);
  }

  public static DBContainer getInstance() {
    if (container == null) {
        container = new DBContainer();
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("DB_URL", container.getJdbcUrl());
    System.setProperty("DB_USERNAME", container.getUsername());
    System.setProperty("DB_PASSWORD", container.getPassword());
  }

  @Override
  public void stop() {
      //do nothing, JVM handles shut down
  }
}