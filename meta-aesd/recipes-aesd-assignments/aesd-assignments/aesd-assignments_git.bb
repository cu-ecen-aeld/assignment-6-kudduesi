LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-kudduesi.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "40ac8f5372e4426bf5f6f536839fe9a7b3afea40"
S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket"

TARGET_LDFLAGS += "-pthread -lrt"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"
INITSCRIPT_PARAMS:${PN} = "defaults"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/aesdsocket ${D}${bindir}/
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/
}
