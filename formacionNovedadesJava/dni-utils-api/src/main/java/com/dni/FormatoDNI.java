package com.dni;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FormatoDNI {
    @Builder.Default
    private boolean puntos=false;
    @Builder.Default
    private boolean cerosDelante=true;
    @Builder.Default
    private boolean mayusculas=true;
    @Builder.Default
    private Character separador=null;
}
