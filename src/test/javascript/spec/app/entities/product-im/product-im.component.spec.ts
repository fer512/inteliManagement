/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InteliManagementTestModule } from '../../../test.module';
import { ProductImComponent } from 'app/entities/product-im/product-im.component';
import { ProductImService } from 'app/entities/product-im/product-im.service';
import { ProductIm } from 'app/shared/model/product-im.model';

describe('Component Tests', () => {
    describe('ProductIm Management Component', () => {
        let comp: ProductImComponent;
        let fixture: ComponentFixture<ProductImComponent>;
        let service: ProductImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProductImComponent],
                providers: []
            })
                .overrideTemplate(ProductImComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProductImComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductImService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProductIm(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.products[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
