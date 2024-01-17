cmake_minimum_required(VERSION 3.5)

project(altim_corr2 LANGUAGES C)

add_executable(altim_corr2 main.c)

include(GNUInstallDirs)
install(TARGETS altim_corr2
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)
