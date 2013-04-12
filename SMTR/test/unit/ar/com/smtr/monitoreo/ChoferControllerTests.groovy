package ar.com.smtr.monitoreo



import org.junit.*
import grails.test.mixin.*

@TestFor(ChoferController)
@Mock(Chofer)
class ChoferControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/chofer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.choferInstanceList.size() == 0
        assert model.choferInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.choferInstance != null
    }

    void testSave() {
        controller.save()

        assert model.choferInstance != null
        assert view == '/chofer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/chofer/show/1'
        assert controller.flash.message != null
        assert Chofer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/chofer/list'

        populateValidParams(params)
        def chofer = new Chofer(params)

        assert chofer.save() != null

        params.id = chofer.id

        def model = controller.show()

        assert model.choferInstance == chofer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/chofer/list'

        populateValidParams(params)
        def chofer = new Chofer(params)

        assert chofer.save() != null

        params.id = chofer.id

        def model = controller.edit()

        assert model.choferInstance == chofer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/chofer/list'

        response.reset()

        populateValidParams(params)
        def chofer = new Chofer(params)

        assert chofer.save() != null

        // test invalid parameters in update
        params.id = chofer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/chofer/edit"
        assert model.choferInstance != null

        chofer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/chofer/show/$chofer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        chofer.clearErrors()

        populateValidParams(params)
        params.id = chofer.id
        params.version = -1
        controller.update()

        assert view == "/chofer/edit"
        assert model.choferInstance != null
        assert model.choferInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/chofer/list'

        response.reset()

        populateValidParams(params)
        def chofer = new Chofer(params)

        assert chofer.save() != null
        assert Chofer.count() == 1

        params.id = chofer.id

        controller.delete()

        assert Chofer.count() == 0
        assert Chofer.get(chofer.id) == null
        assert response.redirectedUrl == '/chofer/list'
    }
}
