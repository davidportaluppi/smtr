package ar.com.smtr.monitoreo

import org.springframework.dao.DataIntegrityViolationException

class ViajeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [viajeInstanceList: Viaje.list(params), viajeInstanceTotal: Viaje.count()]
    }

    def create() {
        [viajeInstance: new Viaje(params)]
    }

    def save() {
        def viajeInstance = new Viaje(params)
        if (!viajeInstance.save(flush: true)) {
            render(view: "create", model: [viajeInstance: viajeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'viaje.label', default: 'Viaje'), viajeInstance.id])
        redirect(action: "show", id: viajeInstance.id)
    }

    def show(Long id) {
        def viajeInstance = Viaje.get(id)
        if (!viajeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaje.label', default: 'Viaje'), id])
            redirect(action: "list")
            return
        }

        [viajeInstance: viajeInstance]
    }

    def edit(Long id) {
        def viajeInstance = Viaje.get(id)
        if (!viajeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaje.label', default: 'Viaje'), id])
            redirect(action: "list")
            return
        }

        [viajeInstance: viajeInstance]
    }

    def update(Long id, Long version) {
        def viajeInstance = Viaje.get(id)
        if (!viajeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaje.label', default: 'Viaje'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (viajeInstance.version > version) {
                viajeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'viaje.label', default: 'Viaje')] as Object[],
                          "Another user has updated this Viaje while you were editing")
                render(view: "edit", model: [viajeInstance: viajeInstance])
                return
            }
        }

        viajeInstance.properties = params

        if (!viajeInstance.save(flush: true)) {
            render(view: "edit", model: [viajeInstance: viajeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'viaje.label', default: 'Viaje'), viajeInstance.id])
        redirect(action: "show", id: viajeInstance.id)
    }

    def delete(Long id) {
        def viajeInstance = Viaje.get(id)
        if (!viajeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaje.label', default: 'Viaje'), id])
            redirect(action: "list")
            return
        }

        try {
            viajeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'viaje.label', default: 'Viaje'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'viaje.label', default: 'Viaje'), id])
            redirect(action: "show", id: id)
        }
    }
}
