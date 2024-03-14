# ConectAndes - Pantallas Mobile

## Requisitos de Entorno

La aplicación fue desarrollada utilizando tecnologías como:

- [Kotlin]([Kotlin Programming Language (kotlinlang.org)](https://kotlinlang.org/)) 
- [Android Studio Giraffe]([Android Studio Giraffe | 2022.3.1 (julio de 2023)  | Android Developers](https://developer.android.com/studio/releases/past-releases/as-giraffe-release-notes?hl=es-419)) (2022.3.1 Patch 2)
- Memoria RAM mínimo 4 GB - ideal superior a 8 GB
- Espacio libre en disco 15 GB
- Procesador multihilo de 2 núcleos en adelante con tecnología basada en arquitecturas para 64 bits.

## Instalación

1. Antes de proceder a instalar se deja la ficha técnica del emulador donde fue desarrollada la apk.

    Properties
    avd.ini.displayname              Pixel 6 API 28
    avd.ini.encoding                 UTF-8
    AvdId                            Pixel_6_API_28
    disk.dataPartition.size          6442450944
    fastboot.chosenSnapshotFile
    fastboot.forceChosenSnapshotBoot no
    fastboot.forceColdBoot           no
    fastboot.forceFastBoot           yes
    hw.accelerometer                 yes
    hw.arc                           false
    hw.audioInput                    yes
    hw.battery                       yes
    hw.camera.back                   virtualscene
    hw.camera.front                  emulated
    hw.cpu.ncore                     2
    hw.device.hash2                  MD5:3db3250dab5d0d93b29353040181c7e9
    hw.device.manufacturer           Google
    hw.device.name                   pixel_6
    hw.dPad                          no
    hw.gps                           yes
    hw.gpu.enabled                   yes
    hw.gpu.mode                      auto
    hw.initialOrientation            Portrait
    hw.keyboard                      yes
    hw.lcd.density                   420
    hw.lcd.height                    2400
    hw.lcd.width                     1080
    hw.mainKeys                      no
    hw.ramSize                       1536
    hw.sdCard                        yes
    hw.sensors.orientation           yes
    hw.sensors.proximity             yes
    hw.trackBall                     no
    image.androidVersion.api         28
    image.sysdir.1                   system-images\android-28\google_apis\x86\
    PlayStore.enabled                false
    runtime.network.latency          none
    runtime.network.speed            full
    showDeviceFrame                  yes
    skin.dynamic                     yes
    tag.display                      Google APIs
    tag.id                           google_apis
    vm.heapSize                      228

2. En el siguiente repositorio se encontrará el código fuente y el apk de la aplicación. 

    ```bash
    https://github.com/LuisaTorresMon/conectandesMobile
    ```

3. Puede optar por clonar el repositorio y construir(build project) el proyecto para luego proceder a ejecutar(Run App).

    ```bash
    git clone https://github.com/LuisaTorresMon/conectandesMobile
    ```

4. O puede optar por instalar el apk directamente en dispositivo físico o en un emulador:

    ```bash
    https://github.com/LuisaTorresMon/conectandesMobile
    ```

## Uso

1. Una vez instalada la aplicación(apk) podrá navegar por la funcionalidades de las pantallas de:

    - Listar alarmas (Punto de partida de la aplicación)

    - Editar alarma

    - Crear alarma

    - Activar alarma 

      ## Listar alarmas 

      Esta pantalla muestra un listado de las alarmas creadas en la aplicación con los datos más relevantes de cada alarma. En esta pantalla también tenemos la posibilidad de editar cada una de las alarmas dando tap sobre toda la fila horaria, es decir puede dar tap en la hora o en los minutos o en sus secciones en blando de dicha fila. 

      ## Editar alarma

      Esta pantalla contiene la información proveniente del listado de alarmas, más exactamente de los datos de la alarma seleccionada para editar. Allí podrá encontrar diferentes componentes con los cuales puede cambiar o configurar la alarma seleccionada a editar.

      ## Crear Alarma 

      Para llegar a esta pantalla se puede acceder desde el listado de alarmas dando tap sobre el botón crear alarma. Esta pantalla contiene diferentes componentes con los cuales puede establecer o configurar la alarma su nueva alarma. En caso que no desee continuar con la creación puede dar tap sobre cancelar y se redirigirá hacía el listado de alarmas. Por otra parte en caso que termine el proceso de creación de alarma dando tap en el botón guardar también será redirigido hacía el listado de alarmas.

      ### Consideraciones 

      En la funcionalidad de dar tap sobre el mapa, este abrirá la app de google maps donde podrá establecer un marcador, pero debido a que en está versión del apk no se desarrolló la funcionalidad de seleccionar marcador y guardar la información, el retorno a la pantalla de creación se debe hacer de manera manual a través de los botones de navegabilidad del dispositivo físico o del emulador.

      ## Activar alarma

      Para simular la activación de la alarma se debe dar tap sobre el espacio en blanco del contenedor, allí lanzará una nueva pantalla donde se activará la alarma y tendrá las opciones de detener o posponer dicha alarma.

      
