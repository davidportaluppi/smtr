package ar.com.smtr.monitoreo



import org.junit.*
import grails.test.mixin.*

@TestFor(ViajeController)
@Mock(Viaje)
class ViajeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/viaje/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.viajeInstanceList.size() == 0
        assert model.viajeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.viajeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.viajeInstance != null
        assert view == '/viaje/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/viaje/show/1'
        assert controller.flash.message != null
        assert Viaje.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/viaje/list'

        populateValidParams(params)
        def viaje = new Viaje(params)

        assert viaje.save() != null

        params.id = viaje.id

        def model = controller.show()

        assert model.viajeInstance == viaje
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/viaje/list'

        populateValidParams(params)
        def viaje = new Viaje(params)

        assert viaje.save() != null

        params.id = viaje.id

        def model = controller.edit()

        assert model.viajeInstance == viaje
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/viaje/list'

        response.reset()

        populateValidParams(params)
        def viaje = new Viaje(params)

        assert viaje.save() != null

        // test invalid parameters in update
        params.id = viaje.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/viaje/edit"
        assert model.viajeInstance != null

        viaje.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/viaje/show/$viaje.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        viaje.clearErrors()

        populateValidParams(params)
        params.id = viaje.id
        params.version = -1
        controller.update()

        assert view == "/viaje/edit"
        assert model.viajeInstance != null
        assert model.viajeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/viaje/list'

        response.reset()

        populateValidParams(params)
        def viaje = new Viaje(params)

        assert viaje.save() != null
        assert Viaje.count() == 1

        params.id = viaje.id

        controller.delete()

        assert Viaje.count() == 0
        assert Viaje.get(viaje.id) == null
        assert response.redirectedUrl == '/viaje/list'
    }
}
