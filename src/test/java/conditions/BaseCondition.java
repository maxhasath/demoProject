package conditions;

public interface BaseCondition {

  /**
   * Implement the pre execution logic for the condition
   */
  void preExecute();

  /**
   * Implement the post execution logic for the condition
   */
  void postExecute();
}
