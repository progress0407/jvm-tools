# Serializable

Serializable을 구현해야 Object를 직렬화(File 등)할 수 있다.  
이때 serialVersionUID이 다르면, 기존 Object 필드가 수정됐을시 읽을 수 없다.  
같을 경우 읽을 수 있으며 변경된 필드에 대해서만 무시하고 읽는다.  
읽기를 원하지 않는 필드는 transient 예약어를 붙이자  