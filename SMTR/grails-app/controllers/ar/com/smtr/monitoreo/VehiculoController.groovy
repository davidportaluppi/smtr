package ar.com.smtr.monitoreo

import org.springframework.dao.DataIntegrityViolationException

class VehiculoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [vehiculoInstanceList: Vehiculo.list(params), vehiculoInstanceTotal: Vehiculo.count()]
    }

    def create() {
        [vehiculoInstance: new Vehiculo(params)]
    }

    def save() {
        def vehiculoInstance = new Vehiculo(params)
        if (!vehiculoInstance.save(flush: true)) {
            render(view: "create", model: [vehiculoInstance: vehiculoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), vehiculoInstance.id])
        redirect(action: "show", id: vehiculoInstance.id)
    }

    def show(Long id) {
        def vehiculoInstance = Vehiculo.get(id)
        if (!vehiculoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), id])
            redirect(action: "list")
            return
        }

        [vehiculoInstance: vehiculoInstance]
    }

    def edit(Long id) {
        def vehiculoInstance = Vehiculo.get(id)
        if (!vehiculoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), id])
            redirect(action: "list")
            return
        }

        [vehiculoInstance: vehiculoInstance]
    }

    def update(Long id, Long version) {
        def vehiculoInstance = Vehiculo.get(id)
        if (!vehiculoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (vehiculoInstance.version > version) {
                vehiculoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'vehiculo.label', default: 'Vehiculo')] as Object[],
                          "Another user has updated this Vehiculo while you were editing")
                render(view: "edit", model: [vehiculoInstance: vehiculoInstance])
                return
            }
        }

        vehiculoInstance.properties = params

        if (!vehiculoInstance.save(flush: true)) {
            render(view: "edit", model: [vehiculoInstance: vehiculoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), vehiculoInstance.id])
        redirect(action: "show", id: vehiculoInstance.id)
    }

    def delete(Long id) {
        def vehiculoInstance = Vehiculo.get(id)
        if (!vehiculoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), id])
            redirect(action: "list")
            return
        }

        try {
            vehiculoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), id])
            redirect(action: "show", id: id)
        }
    }
}
